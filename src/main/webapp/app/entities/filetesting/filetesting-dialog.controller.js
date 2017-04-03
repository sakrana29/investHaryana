(function() {
    'use strict';

    angular
        .module('investhryApp')
        .controller('FiletestingDialogController', FiletestingDialogController);

    FiletestingDialogController.$inject = ['$timeout', '$scope', '$stateParams', '$uibModalInstance', 'entity', 'Filetesting','FileManagement','$http'];

    function FiletestingDialogController ($timeout, $scope, $stateParams, $uibModalInstance, entity, Filetesting,FileManagement,$http) {
        var vm = this;

        vm.filetesting = entity;
        vm.clear = clear;
        vm.save = save;

        $timeout(function (){
            angular.element('.form-group:eq(1)>input').focus();
        });

        function clear () {
            $uibModalInstance.dismiss('cancel');
        }

        function save () {
            console.log(vm.filetesting.file);
            vm.isSaving = true;
            if (vm.filetesting.id !== null) {
                Filetesting.update(vm.filetesting, onSaveSuccess, onSaveError);
            } else {
                Filetesting.save(vm.filetesting, onSaveSuccess, onSaveError);
            }
        }

        function onSaveError () {
            vm.isSaving = false;
        }
        function onSaveSuccess (result)
        {
            var file = vm.filetesting.file;
            var uploadUrl = "api/FileManagement";
            var fd = new FormData();
            fd.append('file', file);
            fd.append('filename',result.id);

            $http.post(uploadUrl, fd, {
            transformRequest : angular.identity,
            headers : {
            'Content-Type' : undefined
            }
            }).success(function() {
            console.log('success');
            }).error(function() {
            console.log('error');
            });

            vm.isSaving = false;
            $scope.$emit('investhryApp:filetestingUpdate', result);
            $uibModalInstance.close(result);
        }
    }
})();
