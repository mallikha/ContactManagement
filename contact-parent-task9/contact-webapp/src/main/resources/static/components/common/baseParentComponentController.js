'use strict';

angular.module('myContactApp')
    .service('baseParentComponentController', function () {

        const genericErrorMsg = "Unexpected error. Thank you to contact us to get a issue solving."

        this.currentErrorMsg = "";
        this.currentSuccessMsg = "";

        this.hideAllMsg = function () {
            this.currentSuccessMsg = "";
            this.currentErrorMsg = "";
        }

        this.displaySuccessMsg = function (msg) {
            this.hideAllMsg();
            this.currentSuccessMsg = msg;
        }

        this.getSuccessMsg = function () {
            return this.currentSuccessMsg;
        }

        this.getErrorMsg = function () {
            return this.currentErrorMsg;
        }

        this.displayErrorMsg = function (msg = genericErrorMsg) {
            this.hideAllMsg();
            this.currentErrorMsg = msg;
        }

    });