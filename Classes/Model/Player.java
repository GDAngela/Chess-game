package Classes;

/**
 * Created by jingjinghuang on 9/8/16.
 */
public class Player {

        private String name;
        private boolean isChecked;
        private int color;

        public Player(String Name,int Color) {
            this.name=Name;
            this.color=Color;
            isChecked=false;
        }

        public void setChecked(Boolean bool){
            this.isChecked=bool;
        }

        public String getName(){
            return this.name;
        }
        public boolean getIsChecked(){
            return this.isChecked;
        }
        public int getColor(){
            return color;
        }




}
