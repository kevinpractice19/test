package com.newnoa.govern.entity.vo;

import com.baozun.framework.entity.BaseVo;
import com.newnoa.govern.common.json.ObjectUtils;
import com.newnoa.govern.common.util.Constant;
import com.newnoa.govern.entity.po.Menu;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.servlet.http.HttpServletRequest;
import java.io.Serializable;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class MenuVo extends BaseVo implements Serializable {

        private long menuId;

        private long parentId;

        private String menuName;

        private String menuPath;

        private int menuSort;

        private String parentName;

        private String createTime;

        private String updateTime;

        private List<MenuVo> menuVoList;

        public MenuVo(Menu menu) {
                this.menuId = menu.getId();
                this.parentId = menu.getParentId();
                this.menuName = menu.getMenuName();
                this.menuPath = menu.getMenuPath();
                this.menuSort = menu.getMenuSort();
                this.createTime = super.dateToString(menu.getCreateTime(), Constant.TIME_FORMAT);
                this.updateTime = menu.getUpdateTime() == null ? null : super.dateToString(menu.getUpdateTime(),Constant.TIME_FORMAT);
        }

        public MenuVo(Menu menu, String parentName) {
                this(menu);
                if (parentName == null)
                    this.parentName = "顶层菜单";
                else
                    this.parentName = parentName;
        }


        public MenuVo(HttpServletRequest request) {
                this.menuId = ObjectUtils.objectToLong(request.getParameter("menuId"));
                this.parentId = ObjectUtils.objectToInt(request.getParameter("parentId"));
                this.menuName = ObjectUtils.objectToString(request.getParameter("menuName"));
                this.menuPath = ObjectUtils.objectToString(request.getParameter("menuPath"));
                this.menuSort = ObjectUtils.objectToInt(request.getParameter("menuSort"));
        }


}
