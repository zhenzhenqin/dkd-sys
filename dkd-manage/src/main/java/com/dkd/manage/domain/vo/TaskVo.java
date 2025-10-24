package com.dkd.manage.domain.vo;

import com.dkd.manage.domain.Task;
import com.dkd.manage.domain.TaskType;
import lombok.Data;

@Data
public class TaskVo extends Task {

    // 工单类型
    private TaskType taskType;
}