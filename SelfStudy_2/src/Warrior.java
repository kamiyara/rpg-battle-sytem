public class Warrior extends Character implements Attackable {
	//Original status of War
	protected int def;
	protected boolean defending = false;

	//InitialSetting
	Warrior(String name, int maxHp, int atk, int def, int teamNum) {
		super(name, maxHp, atk, teamNum);
		this.def = def;
	}

	@Override
	//NormalSkill of War(Smash)
	protected double normalSkill() {
		skillRate = 1.1;
		System.out.println("Warrior " + name + "'s Smash");
		return atk * skillRate;
	}

	@Override
	//UniqueSkill of War(Shield Bash)
	protected double uniqueSkill() {
		skillRate = 0.6;
		System.out.println("Warrior " + name + "'s Shidld Bash");
		return (def + atk) * skillRate;
	}

	@Override
	//UniqueAction of War
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
	//SpecialSkill of War
	protected double specialSkill() {
		skillRate = 1.6;
		System.out.println("Warrior " + name + "'s GigaSlash");
		return atk * skillRate;
	}

	@Override
	//BeAttacked of War
	protected void beAttacked(double dmg) {
		if (cheakDead()) {
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

	@Override
	//Cheak Attakable
	public boolean attackable() {
		if (hp <= 0) {
			return false;
		} else {
			return true;
		}
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
