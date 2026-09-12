
public abstract class Character {
	//CommonStatus
	protected String name;
	protected int maxHp;
	protected int hp;
	protected int atk;
	protected int teamNum;
	//CommmonBattle
	protected double skillRate;
	protected double dmg;

	//InitialSetting
	Character(String name, int maxHp, int atk, int teamNum) {
		this.name = name;
		this.maxHp = maxHp;
		this.hp = maxHp;
		this.atk = atk;
		this.teamNum = teamNum;
	}

	//Normal Skill
	protected abstract double normalSkill();

	//Unique Skill
	protected abstract double uniqueSkill();

	//Unique Action
	protected abstract void uniqueAction();

	//Special Skill
	protected abstract double specialSkill();

	//Be Attacked
	protected abstract void beAttacked(double dmg);

	//Cap Hp at 0
	protected void capHp() {
		if (hp < 0) {
			hp = 0;
		}
	}

	//Upper Cap Hp at MaxHp
	protected void upperCapHp() {
		if (hp > maxHp) {
			hp = maxHp;
		}
	}

	//Check dead
	protected boolean checkDead() {
		if (hp <= 0) {
			return true;
		} else {
			return false;
		}
	}

	//Getter
	public String getName() {
		return name;
	}

	public int getMaxHp() {
		return maxHp;
	}

	public int getHp() {
		return hp;
	}

	public int getAtk() {
		return atk;
	}

	public int getTeamNum() {
		return teamNum;
	}

	public abstract String getJob();

	//Setter
	public void setName(String name) {
		this.name = name;
	}

	public void setMaxHp(int maxHp) {
		this.maxHp = maxHp;
	}

	public void setHp(int hp) {
		this.hp = hp;
	}

	public void setAtk(int atk) {
		this.atk = atk;
	}

	public void setTeamNum(int teamNum) {
		this.teamNum = teamNum;
	}

	//Printer
	public void printAllData() {
		System.out.println("Name:" + name + "\tMaxHp:" + maxHp + "\tHp:" + hp + "\tAtk:" + atk + "\tTeam:" + teamNum);
	}

}
