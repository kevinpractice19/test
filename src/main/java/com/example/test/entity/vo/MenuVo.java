package com.example.test.entity.vo;

import com.example.test.entity.po.Menu;
import com.example.test.entity.po.OperationBtn;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.joda.time.DateTime;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
//@NoArgsConstructor
//@AllArgsConstructor
public class MenuVo implements Serializable {

        public MenuVo(){
                operationBtnList = new ArrayList<>();
        }

        private long menuId;

        private long parentId;

        private String menuName;

        private String menuPath;

        private int menuSort;

        private String parentName;

        private int status;

        private String createTime;

        private String updateTime;

        private List<MenuVo> menuVoList;

        private List<OperationBtn> operationBtnList;

        public MenuVo(Menu menu) {
                this.menuId = menu.getId();
                this.parentId = menu.getParentId();
                this.menuName = menu.getMenuName();
                this.menuPath = menu.getMenuPath();
                this.menuSort = menu.getMenuSort();
                this.status = menu.getStatus();
                this.createTime = new DateTime(menu.getCreateTime()).toString("yyyy-MM-dd HH:mm:ss") ;
                this.updateTime = new DateTime(menu.getUpdateTime()).toString("yyyy-MM-dd HH:mm:ss") ;
        }

        public MenuVo(Menu menu, String parentName) {
                this(menu);
                if (parentName == null)
                    this.parentName = "顶层菜单";
                else
                    this.parentName = parentName;
        }





}
