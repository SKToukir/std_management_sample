package bd.scanner.com.studentmanagement;

/**
 * Created by faiza on 8/4/17.
 */

public class Student {

    private int id;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    private String stdName;
    private String stdId;
    private String stdDpt;
    private String stdResult;

    public String getStdName() {
        return stdName;
    }

    public void setStdName(String stdName) {
        this.stdName = stdName;
    }

    public String getStdId() {
        return stdId;
    }

    public void setStdId(String stdId) {
        this.stdId = stdId;
    }

    public String getStdDpt() {
        return stdDpt;
    }

    public void setStdDpt(String stdDpt) {
        this.stdDpt = stdDpt;
    }

    public String getStdResult() {
        return stdResult;
    }

    public void setStdResult(String stdResult) {
        this.stdResult = stdResult;
    }
}
