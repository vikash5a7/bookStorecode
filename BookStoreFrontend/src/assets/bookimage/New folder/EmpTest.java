class EmpTest
{
 public static void main (String[]args){
 final int IS_FULL_TIME=1;

double check=Math.floor(Math.random()*10)%2;
if(check==IS_FULL_TIME){
System.out.println("Emp is present");
}
else{
System.out.println("no emp");
}
}


}