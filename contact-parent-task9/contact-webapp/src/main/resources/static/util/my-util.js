// util functions 
var MyNgUtil = (function () {

    return {

        getIdentifierFromUrl: function (url) {
            var lastIndexOfSlash = url.lastIndexOf("/");
            var newId = url.substring(lastIndexOfSlash + 1);
            return newId;
        },

        getIdentifierFromHeaderLocation: function (response) {
            var location = response.headers('Location');
            return this.getIdentifierFromUrl(location);
        },

        getIndexWithId: function (id, objs) {
            for (var i = 0; i < objs.length; i++) {
                if (objs[i].id == id) {
                    return i;
                }
            }
            return -1;
        }

    }

}())