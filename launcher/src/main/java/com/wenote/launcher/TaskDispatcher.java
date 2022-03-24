package com.wenote.launcher;

import android.content.Context;
import androidx.annotation.UiThread;
import com.wenote.launcher.task.Task;


public class TaskDispatcher {
    private _TaskDispatcher taskDispatcher;
    private static boolean mHasInit = false;
    private static Context mContext;

    private TaskDispatcher() {
    }

    public static void init(Context context) {
        _TaskDispatcher.init(context);
        mHasInit = true;
        mContext = context;
    }

    public static TaskDispatcher createInstance() {
        if (!mHasInit) {
            throw new RuntimeException("must call TaskDispatcher.init first");
        }
        TaskDispatcher dispatcher = new TaskDispatcher();
        dispatcher.taskDispatcher = _TaskDispatcher.createInstance();
        return  dispatcher;
    }

    public TaskDispatcher addTask(Task task) {
        if (task != null) {
          taskDispatcher.addTask(task);
        }
        return this;
    }

    @UiThread
    public void start() {
        taskDispatcher.start();
    }

    public void cancel() {
       taskDispatcher.cancel();
    }

    @UiThread
    public void await() {
        taskDispatcher.await();
    }

    public static Context getContext() {
        return mContext;
    }

}
