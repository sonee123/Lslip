/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, async } from '@angular/core/testing';
import { Observable } from 'rxjs/Observable';
import { HttpHeaders, HttpResponse } from '@angular/common/http';

import { LayslipTestModule } from '../../../test.module';
import { WorkCenterMasterComponent } from '../../../../../../main/webapp/app/entities/work-center-master/work-center-master.component';
import { WorkCenterMasterService } from '../../../../../../main/webapp/app/entities/work-center-master/work-center-master.service';
import { WorkCenterMaster } from '../../../../../../main/webapp/app/entities/work-center-master/work-center-master.model';

describe('Component Tests', () => {

    describe('WorkCenterMaster Management Component', () => {
        let comp: WorkCenterMasterComponent;
        let fixture: ComponentFixture<WorkCenterMasterComponent>;
        let service: WorkCenterMasterService;

        beforeEach(async(() => {
            TestBed.configureTestingModule({
                imports: [LayslipTestModule],
                declarations: [WorkCenterMasterComponent],
                providers: [
                    WorkCenterMasterService
                ]
            })
            .overrideTemplate(WorkCenterMasterComponent, '')
            .compileComponents();
        }));

        beforeEach(() => {
            fixture = TestBed.createComponent(WorkCenterMasterComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(WorkCenterMasterService);
        });

        describe('OnInit', () => {
            it('Should call load all on init', () => {
                // GIVEN
                const headers = new HttpHeaders().append('link', 'link;link');
                spyOn(service, 'query').and.returnValue(Observable.of(new HttpResponse({
                    body: [new WorkCenterMaster(123)],
                    headers
                })));

                // WHEN
                comp.ngOnInit();

                // THEN
                expect(service.query).toHaveBeenCalled();
                expect(comp.workCenterMasters[0]).toEqual(jasmine.objectContaining({id: 123}));
            });
        });
    });

});
