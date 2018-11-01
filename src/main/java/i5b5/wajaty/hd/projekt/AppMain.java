package i5b5.wajaty.hd.projekt;

import i5b5.wajaty.hd.projekt.config.AppConfiguration;
import i5b5.wajaty.hd.projekt.loading.Worker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class AppMain {

    @Autowired
    private Worker worker;

    public static void main(String[] args) {
        final AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext();
        ctx.register(AppConfiguration.class);
        ctx.refresh();
        final AppMain bean = ctx.getBean(AppMain.class);

        bean.runApp(args);
    }

    private void runApp(String [] args){
        worker.loadDwh();
    }
}
