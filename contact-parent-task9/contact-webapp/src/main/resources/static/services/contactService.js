(function () {
    'use strict';
    //IIFE

    angular.module('myContactApp')
        .factory('contactService', ContactService);

    function ContactService($http, $q) {

        var url = 'api/contacts';

        return {
            createContact: function (contact) {
                return $http.post(url, contact)
                    .then(
                        function (response) {
                            var newId = MyNgUtil.getIdentifierFromHeaderLocation(response);
                            return newId;
                        },
                        function (errResponse) {
                            var errorReason = "The contact creation has encountered an error from the server side";
                            console.error(errorReason);
                            var msg = errResponse.data.message;
                            if (msg){
                               console.error("caused by (Error server message) = " + msg);
                            }
                            
                            return $q.reject(errorReason);
                        }
                    );
            },

            fetchAllContacts: function () {
                return $http.get(url)
                    .then(
                        function (response) {
                            var contacts = response.data;

                            if (contacts == null || contacts.length == 0) {
                                console.info('No contact found');
                            }
                            // convert date in string type to a date type
                            for (var i = 0; i < contacts.length; i++) {
                                if (contacts[i].birthday) {
                                    contacts[i].birthday = new Date(contacts[i].birthday);
                                }
                            }
                            return contacts;
                        },
                        function (errResponse) {
                            var errorReason = "The contacts fetching has encountered an error from the server side";
                            console.error(errorReason);
                            return $q.reject(errorReason);
                        }
                    );
            },

			updateContact: function(contact, id){
				return $http.put(url + '/'+id, contact)
						.then(
								function(response){
									return;
								}, 
		                        function (errResponse) {
		                            var errorReason = "The contact update has encountered an error from the server side";
		                            console.error(errorReason);
		                            return $q.reject(errorReason);
		                        }
						);
			},
			
            deleteContact: function (id) {
                return $http.delete(url + '/' + id)
                    .then(
                        function (response) {
                            return;
                        },
                        function (errResponse) {
                            var errorReason = "The contact deletion has encountered an error from the server side";
                            console.error(errorReason);
                            return $q.reject(errorReason);
                        }
                    );
            }

        }
    }

    //END IIFE
})();