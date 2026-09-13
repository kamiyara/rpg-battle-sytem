public class Warrior extends Character {
	//Original status of War
	protected int def;
	protected boolean defending = false;

	//Initial Setting
	Warrior(String name, int maxHp, int atk, int def, int teamNum) {
		super(name, maxHp, atk, teamNum);
		this.def = def;
	}

	@Override
	//NormalSkill Of War(Smash)
	protected double normalSkill() {
		skillRate = 1.1;
		System.out.println("Warrior " + name + "'s Smash");
		return atk * skillRate;
	}

	@Override
	//UniqueSkill Of War(Shield Bash)
	protected double uniqueSkill() {
		skillRate = 0.6;
		System.out.println("Warrior " + name + "'s Shield Bash");
		return (def + atk) * skillRate;
	}

	@Override
	//UniqueAction Of War
	protected void uniqueAction() {
		int recoveryHp = 30;
		int beforeHp = hp;
		defending = true;
		hp += recoveryHp;
		upperCapHp();
		System.out.println("Warrior " + name + " is defending");
		System.out.println("Warrior " + name + " is recovered " + recoveryHp + "Hp");
		System.out.println("Hp:" + beforeHp + "→" + hp);
	}

	@Override
	//SpecialSkill Of War
	protected double specialSkill() {
		skillRate = 1.6;
		System.out.println("Warrior " + name + "'s GigaSlash");
		return atk * skillRate;
	}

	@Override
	//BeAttacked Of War
	protected void beAttacked(double dmg) {
		if (checkDead()) {
			System.out.println("Warrior " + name + " has already been dead");
			return;
		}
		int beforeHp = hp;
		if (defending) {
			dmg -= def;
			defending = false;
			System.out.println("Warrior " + name + "blocked the attack");
		}
		hp -= dmg;
		capHp();
		System.out.println("Warrior " + name + " takes " + (int) dmg + "damages");
		System.out.println("Hp:" + beforeHp + "→" + hp);
	}

	//Getter
	public int getDef() {
		return def;
	}

	public String getDefending() {
		if (defending) {
			return "Defending";
		} else {
			return "NotDefending";
		}
	}

	public String getJob() {
		return "Warrior";
	}

	//Setter
	public void setDef(int def) {
		this.def = def;
	}

	public void setDefending(boolean defending) {
		this.defending = defending;
	}

	//Printer
	@Override
	public void printAllData() {
		super.printAllData();
		System.out.println("Job:Warrior\tDef:" + def + "\tDefencing:" + defending);
	}

}
