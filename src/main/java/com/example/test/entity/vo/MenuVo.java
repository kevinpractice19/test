package com.example.test.entity.vo;

import com.example.test.entity.po.Menu;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class MenuVo implements Serializable {

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
        }

        public MenuVo(Menu menu, String parentName) {
                this(menu);
                if (parentName == null)
                    this.parentName = "顶层菜单";
                else
                    this.parentName = parentName;
        }





}
