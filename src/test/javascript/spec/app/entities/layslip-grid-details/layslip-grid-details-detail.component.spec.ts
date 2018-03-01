/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, async } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs/Observable';

import { LayslipTestModule } from '../../../test.module';
import { LayslipGridDetailsDetailComponent } from '../../../../../../main/webapp/app/entities/layslip-grid-details/layslip-grid-details-detail.component';
import { LayslipGridDetailsService } from '../../../../../../main/webapp/app/entities/layslip-grid-details/layslip-grid-details.service';
import { LayslipGridDetails } from '../../../../../../main/webapp/app/entities/layslip-grid-details/layslip-grid-details.model';

describe('Component Tests', () => {

    describe('LayslipGridDetails Management Detail Component', () => {
        let comp: LayslipGridDetailsDetailComponent;
        let fixture: ComponentFixture<LayslipGridDetailsDetailComponent>;
        let service: LayslipGridDetailsService;

        beforeEach(async(() => {
            TestBed.configureTestingModule({
                imports: [LayslipTestModule],
                declarations: [LayslipGridDetailsDetailComponent],
                providers: [
                    LayslipGridDetailsService
                ]
            })
            .overrideTemplate(LayslipGridDetailsDetailComponent, '')
            .compileComponents();
        }));

        beforeEach(() => {
            fixture = TestBed.createComponent(LayslipGridDetailsDetailComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(LayslipGridDetailsService);
        });

        describe('OnInit', () => {
            it('Should call load all on init', () => {
                // GIVEN

                spyOn(service, 'find').and.returnValue(Observable.of(new HttpResponse({
                    body: new LayslipGridDetails(123)
                })));

                // WHEN
                comp.ngOnInit();

                // THEN
                expect(service.find).toHaveBeenCalledWith(123);
                expect(comp.layslipGridDetails).toEqual(jasmine.objectContaining({id: 123}));
            });
        });
    });

});
