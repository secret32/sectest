/**
 * Created by Administrator on 2014/5/21.
 */

(function ($) {
    $.Page = function () {
        var prePageLabel = "上一页";
        var nextPageLabel = "下一页";
        var toPageLabel = "GO";
        var total = 0;
        var maxItems = 10;
        var showItems, lastShowItems;
        var id = new Date().getTime();

        this.createPage = function (parentId, totalPage) {
            var parentContainer = "#" + parentId;
            if ($(parentContainer) && !isNaN(totalPage)) {
                total = totalPage;
                $(parentContainer).empty();
                $(parentContainer).append(setPrePage() + setPages() + setNextPage() + setInputPage() + setToPage());
            } else {
                return false;
            }
        };

        function setPrePage() {
            var html = (" <a class=\"prePage" + id + "\" href=\"javascript:void(0)\">" + prePageLabel + "</a> ");
            return html;
        };

        function setNextPage() {
            var html = (" <a class=\"nextPage" + id + "\" href=\"javascript:void(0)\">" + nextPageLabel + "</a> ");
            return html;
        };

        function setPages() {
            initShowItems();
            var html = "";
            if (total <= maxItems) {
                for (var i = 1; i <= total; i++) {
                    html += (" <a class=\"page" + id + "\" href=\"javascript:void(0)\">" + i + "</a> ");
                }
            } else {
                // TODO
                for (var i = 1; i <= showItems; i++) {
                    html += (" <a class=\"page" + id + "\" href=\"javascript:void(0)\">" + i + "</a> ");
                }
                html += " ... ";
                for (var i = lastShowItems - 1; i >= 0; i--) {
                    html += (" <a class=\"page" + id + "\" href=\"javascript:void(0)\">" + (total - i) + "</a> ");
                }
            }
            return html;
        };

        function setInputPage() {
            var html = (" <input id=\"toPageInput" + id + "\" type=\"text\" style=\"width:30px;\"> ");
            return html;
        };

        function setToPage() {
            var html = (" <a class=\"toPage" + id + "\" href=\"javascript:void(0)\">" + toPageLabel + "</a> ");
            return html;
        };

        function initShowItems(op) {
            showItems = Math.ceil((maxItems-3) / 2);
            lastShowItems = showItems - 1;
        };

        this.setPageLabel = function (preLabel, nextLabel, toLabel) {
            if (preLabel)
                prePageLabel = preLabel;
            if (nextLabel)
                nextPageLabel = nextLabel;
            if (toLabel)
                toPageLabel = toLabel;
        };
        this.setMaxItems = function (count) {
            if (!isNaN(count)) {
                if (count <= 10 && count >= 5)
                    maxItems = count;
            }
        };
    }
})(jQuery);
