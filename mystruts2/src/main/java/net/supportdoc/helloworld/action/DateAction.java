package net.supportdoc.helloworld.action;
import com.opensymphony.xwork2.ActionSupport;
import net.supportdoc.helloworld.model.DateModel;
import java.util.Objects;

public class DateAction extends ActionSupport {

    /**
     *
     */
    private static final long serialVersionUID = 1L;
    private DateModel dm;

    public void dmExecute() {

        if (Objects.equals(dm, null)){
            dm = new DateModel();
            System.out.println("【DateAction】success");
        }
    }

    public DateModel getDm() {
        System.out.println("【DateAction.getDm】success");
        return dm;
    }

    public void setDm(DateModel dm) {
        System.out.println("【DateAction.setDm】success");
        this.dm = dm;
    }


}