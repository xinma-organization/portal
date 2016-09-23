~(function() {
    function setting() {
        // viewport的宽度设置为视口大小乘以设备dpr，固定初始1倍缩放
        var meta = document.createElement("meta"),
            dpr = window.devicePixelRatio,
            screenW = window.screen.width * dpr + "px",
            _time = null;
        meta.setAttribute("name", "viewport");
        meta.setAttribute("content", "user-scalable=no,width=" + parseFloat(screenW));
        document.head.appendChild(meta);

        // 为html设置一个dpr属性，为以后计算作为参考
        document.documentElement.setAttribute('data-dpr', dpr);

        // 重置rem方法
        function refreshRem() {
            var rem = parseFloat(screenW) / 10;
            document.documentElement.style.fontSize = rem + 'px';
        }

        // 监听pageshow，防止存缓存中读取
        window.addEventListener('pageshow', function(e) {
            if (e.persisted) {
                clearTimeout(_time);
                _time = setTimeout(refreshRem, 300);
            }
        });

        // 监听方式，重置body字号
        document.addEventListener('DOMContentLoaded', function() {
            document.body.style.fontSize = 16 + 'px';
        });

        // 直接调用，保证rem被重置
        refreshRem();
    }

    // var userAgent = window.navigator.userAgent,
    //     isMoblie = (!!userAgent.match(/iphone/i)) || (!!userAgent.match(/ipad/i)) || (!!userAgent.match(/Android/i));
    // if (!isMoblie) {
    //     throw new Error("请在移动端打开！");
    // }
    setting();
})(); //直接执行，防止jQuery的ready优先调用出现错误
