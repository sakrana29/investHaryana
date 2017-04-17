(function() {
    'use strict';

    angular
        .module('investhryApp')
        .controller('UserManagementDialogController',UserManagementDialogController);

    UserManagementDialogController.$inject = ['$state', 'entity', 'User', 'JhiLanguageService'];

    function UserManagementDialogController ($state, entity, User, JhiLanguageService) {
        var vm = this;

        vm.authorities = ['ROLE_USER', 'ROLE_ADMIN', 'ROLE_RM', 'ROLE_DESK_OFFICER', 'ROLE_NODAL_OFFICER', 'ROLE_ACCOUNT_OFFICER', 'ROLE_FIELD_OFFICER'];
        vm.clear = clear;
        vm.languages = null;
        vm.save = save;
        vm.user = entity;


        JhiLanguageService.getAll().then(function (languages) {
            vm.languages = languages;
        });

        function clear () {
          	$state.go('user-management', null, { reload: true });
        }

        function onSaveSuccess (result) {
           	vm.isSaving = false;
        	$state.go('user-management', null, { reload: true });
        }

        function onSaveError () {
            vm.isSaving = false;
        }

        function save () {
            vm.isSaving = true;
            if (vm.user.id !== null) {
                User.update(vm.user, onSaveSuccess, onSaveError);
            } else {
                User.save(vm.user, onSaveSuccess, onSaveError);
            }
        }
    }
})();
