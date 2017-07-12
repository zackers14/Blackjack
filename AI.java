package BlackJack;

/**
 * Created by ZACK MERICLE on 7/10/2017.
 */
public class AI extends Player {

    public AI(){
        this.comp = true;
        this.handSum = 0;
    }

    public boolean CalculateAction(int sum){
        if (sum > 16){
            this.ChangePlayerActionToStay();
            return false;
        }
        return true;
    }

    public void AIBust(int sum){
        if (sum > 21){
            this.fold = true;
            ChangePlayerActionToStay();
        }
    }
}
