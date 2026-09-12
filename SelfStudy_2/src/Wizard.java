
public class Wizard extends Character implements Attackable {
	//Original Status Of Wiz
	protected int maxMp;
	protected int mp;

	//Consumed Mp
	protected int consumeMp;

	//Initial Setting
	Wizard(String name, int maxHp, int atk, int maxMp, int teamNum) {
		super(name, maxHp, atk, teamNum);
		this.maxMp = maxMp;
		this.mp = maxMp;

	}

	@Override
	//Normal Skill Of Wiz(FireBall)
	protected double normalSkill() {
		consumeMp = 20;
		if (cheakMpRemain(consumeMp)) {
			skillRate = 1.2;
			dmg = atk * skillRate;
			mp -= consumeMp;
			System.out.println("Wizard " + name + "'s FireBall");
			return dmg;
		} else {
			displayLowMpMessage();
			return 0;
		}
	}

	@Override
	//UniqueSkill Of Wiz(Magic Attack)
	protected double uniqueSkill() {
		consumeMp = 40;
		if (cheakMpRemain(consumeMp)) {
			skillRate = 1.4;
			dmg = atk * skillRate;
			mp -= consumeMp;
			System.out.println("Wizard " + name + "''s Magic Attack");
			return dmg;
		} else {
			displayLowMpMessage();
			return 0;
		}
	}

	@Override
	//UniqueAction of Wiz(Recovering Mp)
	protected void uniqueAction() {
		int mpRecovery = 50;
		int beforeMp = mp;
		mp += mpRecovery;
		upperCapMp();
		System.out.println("Wizard " + name + " recovers " + mpRecovery + "Mp");
		System.out.println("Mp:" + beforeMp + "→" + mp);
	}

	@Override
	//SpecialSkill Of Wiz(Explosion)
	protected double specialSkill() {
		consumeMp = mp;
		if (cheakMpRemain(consumeMp)) {
			skillRate = 1.4;
			dmg = atk + mp * skillRate;
			mp -= consumeMp;
			System.out.println("Wizard " + name + "''s Explosion");
			return dmg;
		} else {
			displayLowMpMessage();
			return 0;
		}
	}

	@Override
	//BeAttacked Of Wiz
	protected void beAttacked(double dmg) {
		if (cheakDead()) {
			System.out.println("Wizard " + name + " has already been dead.");
			return;
		}
		int beforeHp = hp;
		hp -= dmg;
		capHp();
		System.out.println("Wizard " + name + " takes " + (int) dmg + "damages");
		System.out.println("Hp:" + beforeHp + "→" + hp);
	}

	@Override
	//Cheak Attakable
	public boolean attackable() {
		if (hp <= 0) {
			return false;
		} else {
			return true;
		}
	}

	//Chack Mp Remain
	private boolean cheakMpRemain(int consumeMp) {
		if (mp >= consumeMp) {
			return true;
		} else {
			return false;
		}
	}

	//Upper Cap Hp At MaxMp
	private void upperCapMp() {
		if (mp > maxMp) {
			mp = maxMp;
		}
	}

	//Display Low Mp Message
	private void displayLowMpMessage() {
		System.out.println(name + " doesn't have enough Mp");
	}

	//Getter
	public int getMaxMp() {
		return maxMp;
	}

	public int getMp() {
		return mp;
	}

	public String getJob() {
		return "Wizard";
	}

	//Setter
	public void setMaxMp(int maxMp) {
		this.maxMp = maxMp;
	}

	public void setMp(int mp) {
		this.mp = mp;
	}

	//Printer
	@Override
	public void printAllData() {
		super.printAllData();
		System.out.println("Job:Wizard\tMaxMp:" + maxMp + "\tMp:" + mp);
	}

}
