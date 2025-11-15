package crm.personnal.scrimlab.domain.bo;

import crm.personnal.scrimlab.config.domain.BaseBO;

import java.time.LocalDate;
import java.util.Date;

public class PrizeListBO implements BaseBO {
    private String playerEmail;
    private String prizeListName;
    private int result;
    private LocalDate tournamentDate;

    public PrizeListBO(String playerEmail, String prizeListName, int result, LocalDate tournamentDate) {
        this.playerEmail = playerEmail;
        this.prizeListName = prizeListName;
        this.result = result;
        this.tournamentDate = tournamentDate;
    }

    public String getPlayerEmail() {
        return playerEmail;
    }

    public void setPlayerEmail(String playerEmail) {
        this.playerEmail = playerEmail;
    }

    public String getPrizeListName() {
        return prizeListName;
    }

    public void setPrizeListName(String prizeListName) {
        this.prizeListName = prizeListName;
    }

    public int getResult() {
        return result;
    }

    public void setResult(int result) {
        this.result = result;
    }

    public LocalDate getTournamentDate() {
        return tournamentDate;
    }

    public void setTournamentDate(LocalDate tournamentDate) {
        this.tournamentDate = tournamentDate;
    }
}
