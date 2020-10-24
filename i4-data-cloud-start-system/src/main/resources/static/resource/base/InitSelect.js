/***********************************封装select下拉框各种渲染，多选，树级多选…… @wangjc**********************************/
var InitSelect = {

    /**
     * 初始化xmselect下拉树级多选
     * @param elem：选择容器
     * @param url：数据接口
     */
    treeSelect:function(elem,url,callback = null){
        var data = BaseAjax.getData(url);
        var res = xmSelect.render({
            el: elem,
            autoRow: true,//当需要选择很多选项时, 横向滚动满足不了你的需求, 可以开启自动换行,可配合showCount，+1
            size: 'medium',//尺寸，最小mini，默认medium，大标large
            theme: {
                color: '#333333',//黑色
            },
            toolbar: {
                show: true
            },
            model: {
                label: {
                    type: 'block',
                    block: {
                        showCount: 1,//最大显示数量, 0:不限制
                        showIcon: true,//是否显示删除图标
                    }
                }
            },
            tree: {
                show: true,
                showFolderIcon: true,
                showLine: true
            },
            filterable: true,//开启搜索
            on:callback,
            height: "400px",
            data:function(){
                return data.result
            }
        });
        return res;
    },
    /**
     * 静态数据初始化下拉多选
     * @param elem
     * @param data
     */
    selectByData:function(elem,data,callback = null){
        var res = xmSelect.render({
            el: elem,
            autoRow: true,//当需要选择很多选项时, 横向滚动满足不了你的需求, 可以开启自动换行,可配合showCount，+1
            size: 'medium',//尺寸，最小mini，默认medium，大标large
            theme: {
                color: '#333333',//黑色
            },
            toolbar: {
                show: true
            },
            model: {
                label: {
                    type: 'block',
                    block: {
                        showCount: 1,//最大显示数量, 0:不限制
                        showIcon: true,//是否显示删除图标
                    }
                }
            },
            on:callback,
            filterable: true,//开启搜索
            height: "400px",
            data:function(){
                return data
            }
        });
        return res;
    },
    /**
     * 初始化xmselect下拉树级单选
     * @param elem
     * @param url
     * @param parentFlag：父节点是否允许被选中，默认不行
     */
    treeSelectByOne:function(elem,url,parentFlag,callback = null){
        var data = BaseAjax.getData(url);
        var list = data.result;
        if(parentFlag){

        }else{
            $.each(list,function(i,o){
                o.disabled = true;//父节点设置不可选，顶层节点
                $.each(o.children,function(n,m){
                    m.disabled = true;//父节点设置不可选，中间层节点
                });
            });
        }
        var res = xmSelect.render({
            el: elem,
            autoRow: true,//当需要选择很多选项时, 横向滚动满足不了你的需求, 可以开启自动换行,可配合showCount，+1
            size: 'medium',//尺寸，最小mini，默认medium，大标large
            theme: {
                color: '#333333',//黑色
            },
            toolbar: {
                show: true
            },
            radio: true,
            clickClose: true,
            model: {
                label: {
                    type: 'block',
                    block: {
                        showCount: 1,//最大显示数量, 0:不限制
                        showIcon: true,//是否显示删除图标
                    }
                }
            },
            on:callback,
            tree: {
                show: true,
                strict: false,
                expandedKeys: [ -1 ],
            },
            filterable: true,//开启搜索
            height: "400px",
            data:function(){
                return list;
            }
        });
        return res;
    }
};