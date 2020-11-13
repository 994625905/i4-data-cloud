package cn.i4.data.cloud.core.entity.dto;

import cn.i4.data.cloud.base.entity.dto.BaseDto;
import cn.i4.data.cloud.core.entity.model.AfternoonTeaSelectModel;
import cn.i4.data.cloud.core.entity.view.AfternoonTeaSelectView;

import java.util.List;

/**
* Dto
* @author wangjc
* @date 2020-11-10 19:54:04
*/
public class AfternoonTeaSelectDto extends BaseDto<AfternoonTeaSelectView> {

    private static final long serialVersionUID = 1605009244128L;

    private String teaName;

    private List<AfternoonTeaSelectModel> selectList;

    private Integer taskId;
    private Integer menuId;

    public String getTeaName() {
        return teaName;
    }

    public void setTeaName(String teaName) {
        this.teaName = teaName;
    }

    public List<AfternoonTeaSelectModel> getSelectList() {
        return selectList;
    }

    public void setSelectList(List<AfternoonTeaSelectModel> selectList) {
        this.selectList = selectList;
    }

    public Integer getTaskId() {
        return taskId;
    }

    public void setTaskId(Integer taskId) {
        this.taskId = taskId;
    }

    public Integer getMenuId() {
        return menuId;
    }

    public void setMenuId(Integer menuId) {
        this.menuId = menuId;
    }
}
