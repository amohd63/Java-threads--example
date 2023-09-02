public class UserThread extends Thread{
    private String userName;
    private int requiredSpace;

    public UserThread() {
    }

    public UserThread(String userName, int requiredSpace) {
        this.userName = userName;
        this.requiredSpace = requiredSpace;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public int getRequiredSpace() {
        return requiredSpace;
    }

    public void setRequiredSpace(int requiredSpace) {
        this.requiredSpace = requiredSpace;
    }

    @Override
    public void run() {
        System.out.println(Thread.currentThread().getName() + " " + this.getUserName() + " " + this.getRequiredSpace());
        CloudService cloudService = CloudService.getInstance();
        cloudService.requestServerSpace(this.requiredSpace, this.userName);
    }
}
