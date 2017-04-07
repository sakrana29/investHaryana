(function() {
    'use strict';

    angular
        .module('investhryApp')
        .controller('viewprojectdetailController', addprojectController);

    addprojectController.$inject = ['$scope', 'Principal', 'LoginService', '$state','Projectlist'];

    function addprojectController ($scope, Principal, LoginService, $state,Projectlist) {
        var vm = this;

        vm.account = null;
        vm.isAuthenticated = null;
        vm.login = LoginService.open;
        vm.register = register;
        $scope.$on('authenticationSuccess', function() {
            getAccount();
        });

        getAccount();

        function getAccount() {
            Principal.identity().then(function(account) {
                vm.account = account;
                vm.isAuthenticated = Principal.isAuthenticated;
            });
        }
        function register () {
            $state.go('register');
        }

        vm.projectcompletedetail=[];

        loadAll();

        function loadAll() {
            Projectlist.query(function(result){
                vm.projectlist=result;
//                console.log(vm.projectcompletedetail[0].projectdetailDTO);
            });
        }
    }
})();
