package bone.server.server.model.skill.skills;

import bone.server.server.model.L1Character;
import bone.server.server.model.Instance.L1PcInstance;
import bone.server.server.serverpackets.S_SkillIconShield;

public class EarthSkin {

	public static void runSkill(L1Character cha, int buffIconDuration) {
		L1PcInstance pc = (L1PcInstance) cha;
		pc.getAC().addAc(-6);
		pc.sendPackets(new S_SkillIconShield(6, buffIconDuration));
	}

}
