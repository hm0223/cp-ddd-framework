package ddd.plus.showcase.wms.app.service.dto;

import ddd.plus.showcase.wms.app.service.dto.base.ApiRequest;
import lombok.Data;

/**
 * 操作员领取复核任务.
 */
@Data
public class ClaimTaskRequest extends ApiRequest {
    // JSR303 ignored here
    private String containerNo;
    private String platformNo;
    // 领取任务时是否提示打成几个包裹
    public Boolean recommendPackQty;

}
