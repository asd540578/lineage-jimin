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

package bone.server.server.clientpackets;

import server.LineageClient;
import bone.server.server.model.Broadcaster;
import bone.server.server.model.L1Object;
import bone.server.server.model.L1Trade;
import bone.server.server.model.L1World;
import bone.server.server.model.Instance.L1BuffNpcInstance;
import bone.server.server.model.Instance.L1PcInstance;
import bone.server.server.serverpackets.S_NpcChatPacket;
import bone.server.server.serverpackets.S_ServerMessage;

// Referenced classes of package bone.server.server.clientpackets:
// ClientBasePacket

public class C_TradeOK extends ClientBasePacket {

	private static final String C_TRADE_CANCEL = "[C] C_TradeOK";

	public C_TradeOK(byte abyte0[], LineageClient clientthread) throws Exception {
		super(abyte0);

		L1PcInstance player = clientthread.getActiveChar();
		L1Object trading_partner = L1World.getInstance().findObject(player.getTradeID());
		
		if (trading_partner != null) {
			if(trading_partner instanceof L1PcInstance){
				L1PcInstance target = (L1PcInstance)trading_partner;
				player.setTradeOk(true);
				if (player.getTradeOk() && target.getTradeOk()) // 모두 OK를 눌렀다
				{
					// (180 - 16) 개미만이라면 트레이드 성립.
					// 본래는 겹치는 아이템(아데나등 )을 이미 가지고 있는 경우를 고려하지 않는 차면 안 된다.
					if (player.getInventory().getSize() < (180 - 16)
							&& target.getInventory().getSize() < (180 - 16)) {// 서로의 아이템을 상대에게 건네준다				
						L1Trade trade = new L1Trade();
						trade.TradeOK(player);
					} else {// 서로의 아이템을 수중에 되돌린다				
						player.sendPackets(new S_ServerMessage(263)); // \f1한사람의 캐릭터가 가지고 걸을 수 있는 아이템은 최대 180개까지입니다.
						target.sendPackets(new S_ServerMessage(263)); // \f1한사람의 캐릭터가 가지고 걸을 수 있는 아이템은 최대 180개까지입니다.
						L1Trade trade = new L1Trade();
						trade.TradeCancel(player);
					}
				}
			}else if(trading_partner instanceof L1BuffNpcInstance){
				L1BuffNpcInstance target = (L1BuffNpcInstance)trading_partner;
				player.setTradeOk(true);
				if (player.getTradeOk()) // 모두 OK를 눌렀다
				{
					if(player.getTradeWindowInventory().findItemId(40308).getCount() != 30000){
						L1Trade trade = new L1Trade();
						trade.TradeCancel(player);
						Broadcaster.broadcastPacket(target, new S_NpcChatPacket(target, "30000원만", 0));
						return;
					}
					// (180 - 16) 개미만이라면 트레이드 성립.
					// 본래는 겹치는 아이템(아데나등 )을 이미 가지고 있는 경우를 고려하지 않는 차면 안 된다.
					if (player.getInventory().getSize() < (180 - 16)
							&& target.getInventory().getSize() < (180 - 16)) {// 서로의 아이템을 상대에게 건네준다				
						L1Trade trade = new L1Trade();
						trade.TradeOK(player);
					} else {// 서로의 아이템을 수중에 되돌린다				
						player.sendPackets(new S_ServerMessage(263)); // \f1한사람의 캐릭터가 가지고 걸을 수 있는 아이템은 최대 180개까지입니다.
						L1Trade trade = new L1Trade();
						trade.TradeCancel(player);
					}
				}
			}
		}
	}

	@Override
	public String getType() {
		return C_TRADE_CANCEL;
	}

}
