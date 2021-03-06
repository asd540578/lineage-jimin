/*
 * This program is free software; you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation; either version 2, or (at your option)
 * any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program; if not, write to the Free Software
 * Foundation, Inc., 59 Temple Place - Suite 330, Boston, MA
 * 02111-1307, USA.
 *
 * http://www.gnu.org/copyleft/gpl.html
 */
package bone.server.server.TimeController;

import java.util.Calendar;
import java.util.TimeZone;

import bone.server.Config;
import bone.server.server.datatables.AuctionBoardTable;
import bone.server.server.datatables.ClanTable;
import bone.server.server.datatables.HouseTable;
import bone.server.server.model.L1Clan;
import bone.server.server.model.L1World;
import bone.server.server.templates.L1AuctionBoard;
import bone.server.server.templates.L1House;

public class HouseTaxTimeController extends Thread {
	private static HouseTaxTimeController _instance;

	public static HouseTaxTimeController getInstance() {
		if (_instance == null) {
			_instance = new HouseTaxTimeController();
			_instance.start();
		}
		return _instance;
	}

	public void run() {
		System.out.println(HouseTaxTimeController.class.getName()  + " 시작");
		try {
			while (true) {
				checkTaxDeadline();
				Thread.sleep(600000);
			}
		} catch (Exception e1) {
		}
	}

	public Calendar getRealTime() {
		TimeZone tz = TimeZone.getTimeZone(Config.TIME_ZONE);
		Calendar cal = Calendar.getInstance(tz);
		return cal;
	}

	private void checkTaxDeadline() {
		for (L1House house : HouseTable.getInstance().getHouseTableList()) {
			if (!house.isOnSale()) { // 경매중의 아지트는 체크하지 않는다
				if (house.getTaxDeadline().before(getRealTime())) {
					sellHouse(house);
				}
			}
		}
	}
	
	private void sellHouse(L1House house) {
		AuctionBoardTable boardTable = new AuctionBoardTable();
		L1AuctionBoard board = new L1AuctionBoard();
		if (board != null) {
			// 경매 게시판에 신규 기입
			int houseId = house.getHouseId();
			board.setHouseId(houseId);
			board.setHouseName(house.getHouseName());
			board.setHouseArea(house.getHouseArea());
			TimeZone tz = TimeZone.getTimeZone(Config.TIME_ZONE);
			Calendar cal = Calendar.getInstance(tz);
			cal.add(Calendar.DATE, 5); // 5일 후
			cal.set(Calendar.MINUTE, 0); // 분 , 초는 잘라서 버림
			cal.set(Calendar.SECOND, 0);
			board.setDeadline(cal);
			board.setPrice(100000);
			board.setLocation(house.getLocation());
			board.setOldOwner("");
			board.setOldOwnerId(0);
			board.setBidder("");
			board.setBidderId(0);
			boardTable.insertAuctionBoard(board);
			house.setOnSale(true); // 경매중으로 설정
			house.setPurchaseBasement(true); // 지하 아지트미구입으로 설정
			cal.add(Calendar.DATE, Config.HOUSE_TAX_INTERVAL);
			house.setTaxDeadline(cal);
			HouseTable.getInstance().updateHouse(house); // DB에 기입해
			// 이전의 소유자의 아지트를 지운다
			for (L1Clan clan : L1World.getInstance().getAllClans()) {
				if (clan.getHouseId() == houseId) {
					clan.setHouseId(0);
					ClanTable.getInstance().updateClan(clan);
				}
			}
		}
	}

}
