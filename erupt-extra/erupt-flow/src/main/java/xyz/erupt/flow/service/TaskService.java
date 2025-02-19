package xyz.erupt.flow.service;

import com.baomidou.mybatisplus.extension.service.IService;
import xyz.erupt.flow.bean.entity.OaTask;
import xyz.erupt.flow.bean.vo.OrgTreeVo;
import xyz.erupt.flow.bean.vo.TaskDetailVo;

import java.util.List;
import java.util.Set;

public interface TaskService extends IService<OaTask>, WithListener {

    /**
     * 完成任务
     * @param taskId
     */
    public OaTask complete(Long taskId, String account, String accountName, String remarks, String content);

    /**
     * 完成任务
     * @param taskId
     */
    public OaTask complete(Long taskId, String remarks, String content);

    /**
     * 转办任务，只能是或签
     * @param taskId
     */
    public void assign(Long taskId, Set<OrgTreeVo> userIds, String remarks);

    /**
     * 拒绝任务
     * @param taskId
     */
    public void refuse(Long taskId, String account, String accountName , String remarks, String content);

    /**
     * 拒绝任务
     * @param taskId
     */
    public void refuse(Long taskId, String remarks, String content);

    /**
     * 查询当前流程的待完成任务
     */
    List<OaTask> getTasksByActivityId(Long activityId, String... types);

    /**
     * 分页查询我的任务
     */
    List<OaTask> listMyTasks(String keywords);

    void removeByProcessInstId(Long id);

    boolean activeTaskByActivityId(Long activityId);

    List<OaTask> listByActivityId(Long activityId, boolean activied);

    void saveBatchWithUserLink(List<OaTask> oaTasks);

    /**
     * 查看任务详情
     */
    TaskDetailVo getTaskDetail(Long taskId);

    /**
     * 查看实例详情
     */
    TaskDetailVo getInstDetail(Long instId);

    /**
     * 中断所有线程
     * @param executionId
     * @param reason
     */
    void stopByExecutionId(Long executionId, String reason);

    void stopByInstId(Long instId, String reason);

    /**
     *
     * @param id
     */
    List<OaTask> listByInstanceId(Long id);
}
