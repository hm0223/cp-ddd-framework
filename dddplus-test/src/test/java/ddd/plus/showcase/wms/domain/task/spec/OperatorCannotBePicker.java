package ddd.plus.showcase.wms.domain.task.spec;

import ddd.plus.showcase.wms.domain.common.ExceptionCode;
import ddd.plus.showcase.wms.domain.common.MasterDataGateway;
import ddd.plus.showcase.wms.domain.common.Operator;
import ddd.plus.showcase.wms.domain.task.Task;
import io.github.dddplus.buddy.specification.AbstractSpecification;
import io.github.dddplus.buddy.specification.Notification;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * 业务方明确提出：拣货人员不能自己完成复核任务，就像(出纳，会计)必须分离一样.
 */
@Slf4j
@AllArgsConstructor
public class OperatorCannotBePicker extends AbstractSpecification<Task> {
    private final MasterDataGateway masterDataGateway;
    private final Operator candidate;

    @Override

    public boolean isSatisfiedBy(Task task, Notification notification) {
        if (!masterDataGateway.allowPerformChecking(candidate)) {
            notification.addError(ExceptionCode.OperatorDisallowed.error());
            return false;
        }

        return true;
    }
}
