var tab;
layui.define(['element', 'nprogress', 'form', 'table',  'tab', 'navbar','laytpl'], function(exports) {
    var $ = layui.jquery,
        element = layui.element,
        layer = layui.layer,
        _win = $(window),
        _doc = $(document),
        _body = $('.kit-body'),
        form = layui.form,
        table = layui.table,
        navbar = layui.navbar;
    	tab = layui.tab
    var main = {
        hello: function(str) {
            layer.alert('Hello ' + (str || 'test'));
        },
        config: {
            type: 'iframe'
        },
        set: function(options) {
            var that = this;
            $.extend(true, that.config, options);
            return that;
        },
        init: function() {
            var that = this,
                _config = that.config;
           
            if (_config.type === 'page') {
                tab.set({
                    renderType: 'page',
                    mainUrl: 'table.html',
                    elem: '#container',
                    onSwitch: function(data) { //选项卡切换时触发
                       
                    },
                    closeBefore: function(data) { //关闭选项卡之前触发                       
                        return true; //返回true则关闭
                    }
                }).render();
               
                navbar.bind(function(data) {
                    tab.tabAdd(data);
                });
            }
            if (_config.type === 'iframe') {
                tab.set({                  
                    elem: '#container',
                    onSwitch: function(data) { //选项卡切换时触发
                        
                    },
                    closeBefore: function(data) { //关闭选项卡之前触发
                       
                        return true; //返回true则关闭
                    }
                }).render();
               
                navbar.bind(function(data) {
                    tab.tabAdd(data);
                });
               
            }
            return that;
        }
    };
    //输出接口
    exports('main', main);
});


