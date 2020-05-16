(function () {
    'use strict'; //IIFE


    var regexMock = new RegExp("/\?.+mock");
    if (!document.URL.match(regexMock)) {
        return;
    }

    var myAppDev = angular.module('myContactApp')

    .config(function ($provide) {
        $provide.decorator('$httpBackend', angular.mock.e2e.$httpBackendDecorator);
    })

    .run(function ($httpBackend, $http) {

        console.info("mocked backend enabled");

        // state	   	    	
        var contacts = [
            {
                id: 1,
                firstName: 'John',
                lastName: 'Doe',
                birthday: '',
                email: 'johndoe@secure.com',
                phone: '0134345454',
                sex: 'MALE',
                address: {
                    addressLine: '154 Unknown Street',
                    city: 'Ideal City',
                    zip: '10101',
                    country: 'Wanted Country'
                }

            },


            {
                id: 2,
                firstName: 'Jane',
                lastName: 'Calamity',
                birthday: new Date("1852-05-01"),
                email: 'janecalimity@secure.com',
                phone: '0134345454',
                sex: 'MALE',
                address: {
                    addressLine: '123 Billy The Kid Street',
                    city: 'Western City',
                    zip: '99999',
                    country: 'World Wide West'
                }

            }

	    			   ];

        var nextContactId = contacts.length + 1;

        // behavior
        $httpBackend.whenGET(/\.html.*$/).passThrough();

        // GET CONTACTS
        $httpBackend.whenGET("api/contacts").respond(contacts);

        // POST CONTACT
        $httpBackend.whenPOST("api/contacts")
            .respond(function (method, url, data, headers) {
                console.log("mocked response for POST contact with data " + data);
                var contact = angular.fromJson(data);
                contact.id = nextContactId;
                contacts.push(contact);
                var responseMockHeaders = {
                    Location: 'api/contacts/' + contact.id
                };
                nextContactId++;
                return [201, {}, responseMockHeaders];
        });
            
        
        // PUT CONTACT												  
        $httpBackend.whenPUT(/api\/contacts\/\d*/)
            .respond(function (method, url, data, headers) {
                console.log("mock put contact with data " + data);
                var contactModified = angular.fromJson(data);
                var index = MyNgUtil.getIndexWithId(contactModified.id, contacts);
                contacts.splice(index, 1, contactModified);
                return [200, {}, undefined];
            });

        // DELETE CONTACT												  
        $httpBackend.whenDELETE(/api\/contacts\/\d*/)
            .respond(function (method, url, data, headers) {
                console.log("mock delete contact with url " + url);
                var idContact = parseInt(MyNgUtil.getIdentifierFromUrl(url));
                var index = MyNgUtil.getIndexWithId(idContact, contacts);
                if (index != -1) {
                    contacts.splice(index, 1);
                    return [200, {}, undefined];
                }
                return [500, {}, undefined];
            });

    }); // run end		



})(); //END IIFE