(function() {
    'use strict';

    angular
        .module('investhryApp')
        .controller('ProjectCompleteDetailController', ProjectCompleteDetailController);

    ProjectCompleteDetailController.$inject = ['$scope', 'Principal', 'LoginService', '$state','entity','Projectcompletedetail'];

    function ProjectCompleteDetailController ($scope, Principal, LoginService, $state, entity, Projectcompletedetail) {
        var vm = this;

        vm.account = null;
        vm.isAuthenticated = null;
        vm.login = LoginService.open;
        vm.register = register;
        vm.CompleteProjectDetail=entity;

        vm.investor=vm.CompleteProjectDetail.investorDTO;
        vm.companydetail=vm.CompleteProjectDetail.companydetailDTO;
        vm.projectdetail=vm.CompleteProjectDetail.projectdetailDTO;
        vm.projectsitedetail=vm.CompleteProjectDetail.projectsitedetailDTO;
        vm.project_finance_investment=vm.CompleteProjectDetail.project_finance_investmentDTO;
        vm.manufacturing_detail=vm.CompleteProjectDetail.manufacturingdetailDTO;
        vm.electricrequirement=vm.CompleteProjectDetail.electricrequirementDTO;
        vm.projectcombinecodes=vm.CompleteProjectDetail.projectdetailcombinecodesDTO ;

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
    }
})();
