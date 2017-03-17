(function() {
    'use strict';

    angular
        .module('investhryApp')
        .controller('CountryDialogController', CountryDialogController);

    CountryDialogController.$inject = ['$timeout', '$scope', '$state', 'entity', 'Country'];

    function CountryDialogController ($timeout, $scope, $state,  entity, Country) {
        var vm = this;

        vm.country = entity;
        vm.clear = clear;
        vm.save = save;

        $timeout(function (){
            angular.element('.form-group:eq(1)>input').focus();
        });

        function clear () {
            $state.go('country', null, { reload: 'country' });
        }

        function save () {
            vm.isSaving = true;
            if (vm.country.id !== null) {
                Country.update(vm.country, onSaveSuccess, onSaveError);
            } else {
                Country.save(vm.country, onSaveSuccess, onSaveError);
            }
        }

        function onSaveSuccess (result) {
            $scope.$emit('investhryApp:countryUpdate', result);
            $state.go('country', null, { reload: 'country' });
            vm.isSaving = false;
        }

        function onSaveError () {
            vm.isSaving = false;
        }


    }
})();
