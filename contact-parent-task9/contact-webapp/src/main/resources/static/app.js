(function () {
    'use strict';
    //IIFE


    angular.module('myContactApp', ['ui.router', 'ngMessages', 'ngMaterial', 'ngAnimate', 'ngAria'])

    .config(function ($stateProvider, $urlRouterProvider) {

        var states = [
            {
                name: 'manageContacts',
                url: '/manageContacts',
                component: 'contactsManagementView'
		},

            {
                name: 'createContacts',
                url: '/createContacts',
                component: 'contactCreationView'
		},
		  {
            name: 'searchcontact',
            url: '/searchcontact',
            component: 'contactSearchView'
	}

	];

        states.forEach(function (state) {
            $stateProvider.state(state);
        });

        $urlRouterProvider.otherwise('/manageContacts');

    })


    // END IIFE
})();