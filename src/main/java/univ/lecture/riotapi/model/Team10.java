package univ.lecture.riotapi.model;

public class Team10 {
   int teamId;
   long now;
   double result;
   
   
   public Team10(int teamId, long now, double result) {
      super();
      this.teamId = teamId;
      this.now = now;
      this.result = result;
   }
   public int getTeamId() {
      return teamId;
   }
   public void setTeamId(int teamId) {
      this.teamId = teamId;
   }
   public long getNow() {
      return now;
   }
   public void setNow(long now) {
      this.now = now;
   }
   public double getResult() {
      return result;
   }
   public void setResult(double result) {
      this.result = result;
   }
   
}