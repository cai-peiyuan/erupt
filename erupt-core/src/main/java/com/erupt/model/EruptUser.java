package com.erupt.model;

import com.erupt.annotation.Erupt;
import com.erupt.annotation.EruptField;
import com.erupt.annotation.sub_erupt.Power;
import com.erupt.annotation.sub_field.Edit;
import com.erupt.annotation.sub_field.EditType;
import com.erupt.annotation.sub_field.View;
import com.erupt.annotation.sub_field.sub_edit.BoolType;
import com.erupt.annotation.sub_field.sub_edit.InputEnum;
import com.erupt.annotation.sub_field.sub_edit.InputType;
import com.erupt.annotation.sub_field.sub_edit.TabType;
import lombok.Data;

import javax.persistence.*;
import java.util.Set;

/**
 * Created by liyuepeng on 11/22/18.
 */
@Data
@Erupt(
        name = "用户",
        desc = "用户配置",
        power = @Power(
                export = false,
                importable = false
        )
)
@Entity
@Table(name = "E_USER", uniqueConstraints = {
        @UniqueConstraint(columnNames = "accout")
})
public class EruptUser extends BaseModel {

    @Column(name = "account")
    @EruptField(
            views = @View(title = "用户名"),
            edit = @Edit(title = "用户名", desc = "登录用户名", notNull = true)
    )
    private String account;

    @Column(name = "name")
    @EruptField(
            views = @View(title = "姓名"),
            edit = @Edit(title = "姓名", notNull = true)
    )
    private String name;

    @Column(name = "pwd")
    @EruptField(
            views = @View(title = "密码"),
            edit = @Edit(title = "密码", notNull = true)
    )
    private String password;

    @EruptField(
            views = @View(title = "确认密码"),
            edit = @Edit(title = "确认密码", notNull = true)
    )
    private String password2;

    @Column(name = "IS_MD5")
    @EruptField(
            views = @View(title = "md5加密"),
            edit = @Edit(
                    title = "md5加密",
                    type = EditType.BOOLEAN,
                    boolType = @BoolType(
                            trueText = "加密",
                            falseText = "不加密",
                            defaultValue = true
                    )
            )
    )
    private Boolean isMD5;

    @Column(name = "STATUS")
    @EruptField(
            views = @View(title = "状态"),
            edit = @Edit(
                    title = "状态",
                    type = EditType.BOOLEAN,
                    boolType = @BoolType(
                            trueText = "激活",
                            falseText = "锁定",
                            defaultValue = true
                    )
            )
    )
    private Boolean status;

    @ManyToMany
    @JoinTable(
            name = "E_USER_ROLE",
            joinColumns = @JoinColumn(name = "USER_ID", referencedColumnName = "ID"),
            inverseJoinColumns = @JoinColumn(name = "ROLE_ID", referencedColumnName = "ID"))
    @EruptField(
            edit = @Edit(
                    title = "所属角色",
                    type = EditType.TAB,
                    tabType = @TabType
            )
    )
    private Set<EruptRole> roles;

    @Column(name = "WHITE_IP")
    @EruptField(
            edit = @Edit(
                    title = "ip白名单",
                    desc = "ip与ip之间使用换行符间隔，不填表示不对ip进行鉴权管理",
                    inputType = @InputType(type = InputEnum.TEXTAREA)
            )

    )
    private String whiteIp;

    @Column(name = "REMARK")
    @EruptField(
            edit = @Edit(
                    title = "备注",
                    inputType = @InputType(type = InputEnum.TEXTAREA)
            )

    )
    private String remark;

}