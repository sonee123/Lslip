/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, async } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs/Observable';

import { LayslipTestModule } from '../../../test.module';
import { WorkCodeDetailComponent } from '../../../../../../main/webapp/app/entities/work-code/work-code-detail.component';
import { WorkCodeService } from '../../../../../../main/webapp/app/entities/work-code/work-code.service';
import { WorkCode } from '../../../../../../main/webapp/app/entities/work-code/work-code.model';

describe('Component Tests', () => {

    describe('WorkCode Management Detail Component', () => {
        let comp: WorkCodeDetailComponent;
        let fixture: ComponentFixture<WorkCodeDetailComponent>;
        let service: WorkCodeService;

        beforeEach(async(() => {
            TestBed.configureTestingModule({
                imports: [LayslipTestModule],
                declarations: [WorkCodeDetailComponent],
                providers: [
                    WorkCodeService
                ]
            })
            .overrideTemplate(WorkCodeDetailComponent, '')
            .compileComponents();
        }));

        beforeEach(() => {
            fixture = TestBed.createComponent(WorkCodeDetailComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(WorkCodeService);
        });

        describe('OnInit', () => {
            it('Should call load all on init', () => {
                // GIVEN

                spyOn(service, 'find').and.returnValue(Observable.of(new HttpResponse({
                    body: new WorkCode(123)
                })));

                // WHEN
                comp.ngOnInit();

                // THEN
                expect(service.find).toHaveBeenCalledWith(123);
                expect(comp.workCode).toEqual(jasmine.objectContaining({id: 123}));
            });
        });
    });

});
