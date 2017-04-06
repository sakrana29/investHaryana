(function() {
    'use strict';

    angular
        .module('investhryApp')
        .controller('UserManagementController', UserManagementController);

    UserManagementController.$inject = ['Principal', 'User', 'ParseLinks', 'AlertService', 'JhiLanguageService'];

    function UserManagementController(Principal, User, ParseLinks, AlertService, JhiLanguageService) {
        var vm = this;

        vm.authorities = ['ROLE_USER', 'ROLE_ADMIN', 'ROLE_RM', 'ROLE_DESK_OFFICER', 'ROLE_NODAL_OFFICER', 'ROLE_ACCOUNT_OFFICER'];
        vm.currentAccount = null;
        vm.languages = null;
        vm.loadAll = loadAll;
        vm.setActive = setActive;
        vm.users = [];


        vm.loadAll();
        loadByEmail();
        JhiLanguageService.getAll().then(function (languages) {
            vm.languages = languages;
        });
        Principal.identity().then(function(account) {
            vm.currentAccount = account;
        });

        function setActive (user, isActivated) {
            user.activated = isActivated;
            User.update(user, function () {
                vm.loadAll();
                vm.clear();
            });
        }

        function loadAll () {
            User.query(onSuccess, onError);
        }

        function loadByEmail(){
            User.query({email: "dh@hdfhf.com"}, function(data){
                console.log(data);
            });
        }

        function onSuccess(data, headers) {

            vm.users = data;
        }

        function onError(error) {
            AlertService.error(error.data.message);
        }

    }
})();
