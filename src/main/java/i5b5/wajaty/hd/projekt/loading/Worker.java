package i5b5.wajaty.hd.projekt.loading;

import i5b5.wajaty.hd.projekt.loading.tasks.CallTask;
import i5b5.wajaty.hd.projekt.loading.tasks.MessageTask;
import i5b5.wajaty.hd.projekt.loading.tasks.NetworkTask;
import i5b5.wajaty.hd.projekt.loading.tasks.TvTask;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class Worker {

    private final NetworkTask networkTask;
    private final MessageTask messageTask;
    private final CallTask callTask;
    private final TvTask tvTask;

    @Autowired
    public Worker(NetworkTask networkTask, MessageTask messageTask, CallTask callTask, TvTask tvTask) {
        this.networkTask = networkTask;
        this.messageTask = messageTask;
        this.callTask = callTask;
        this.tvTask = tvTask;
    }

    public void loadDwh(){
        networkTask.run();
        messageTask.run();
        callTask.run();
        tvTask.run();
    }
}
