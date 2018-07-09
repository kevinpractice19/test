package com.newnoa.govern.entity.po;

import com.newnoa.govern.entity.vo.MenuVo;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Menu {

        private long id;

        private long parentId;

        private String menuName;

        private String menuPath;

        private int menuSort;

        private Date createTime;

        private Date updateTime;

        private List<Menu> menuList;


        public Menu(MenuVo vo) {
                this.id = vo.getMenuId();
                this.parentId = vo.getParentId();
                this.menuName = vo.getMenuName();
                this.menuPath = vo.getMenuPath();
                this.menuSort = vo.getMenuSort();
        }
}
