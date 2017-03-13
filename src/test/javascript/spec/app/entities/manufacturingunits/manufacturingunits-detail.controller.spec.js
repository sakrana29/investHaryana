'use strict';

describe('Controller Tests', function() {

    describe('Manufacturingunits Management Detail Controller', function() {
        var $scope, $rootScope;
        var MockEntity, MockPreviousState, MockManufacturingunits;
        var createController;

        beforeEach(inject(function($injector) {
            $rootScope = $injector.get('$rootScope');
            $scope = $rootScope.$new();
            MockEntity = jasmine.createSpy('MockEntity');
            MockPreviousState = jasmine.createSpy('MockPreviousState');
            MockManufacturingunits = jasmine.createSpy('MockManufacturingunits');
            

            var locals = {
                '$scope': $scope,
                '$rootScope': $rootScope,
                'entity': MockEntity,
                'previousState': MockPreviousState,
                'Manufacturingunits': MockManufacturingunits
            };
            createController = function() {
                $injector.get('$controller')("ManufacturingunitsDetailController", locals);
            };
        }));


        describe('Root Scope Listening', function() {
            it('Unregisters root scope listener upon scope destruction', function() {
                var eventType = 'investhryApp:manufacturingunitsUpdate';

                createController();
                expect($rootScope.$$listenerCount[eventType]).toEqual(1);

                $scope.$destroy();
                expect($rootScope.$$listenerCount[eventType]).toBeUndefined();
            });
        });
    });

});
