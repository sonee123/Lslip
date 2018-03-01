/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, async } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs/Observable';

import { LayslipTestModule } from '../../../test.module';
import { LayslipRollDetailsDetailComponent } from '../../../../../../main/webapp/app/entities/layslip-roll-details/layslip-roll-details-detail.component';
import { LayslipRollDetailsService } from '../../../../../../main/webapp/app/entities/layslip-roll-details/layslip-roll-details.service';
import { LayslipRollDetails } from '../../../../../../main/webapp/app/entities/layslip-roll-details/layslip-roll-details.model';

describe('Component Tests', () => {

    describe('LayslipRollDetails Management Detail Component', () => {
        let comp: LayslipRollDetailsDetailComponent;
        let fixture: ComponentFixture<LayslipRollDetailsDetailComponent>;
        let service: LayslipRollDetailsService;

        beforeEach(async(() => {
            TestBed.configureTestingModule({
                imports: [LayslipTestModule],
                declarations: [LayslipRollDetailsDetailComponent],
                providers: [
                    LayslipRollDetailsService
                ]
            })
            .overrideTemplate(LayslipRollDetailsDetailComponent, '')
            .compileComponents();
        }));

        beforeEach(() => {
            fixture = TestBed.createComponent(LayslipRollDetailsDetailComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(LayslipRollDetailsService);
        });

        describe('OnInit', () => {
            it('Should call load all on init', () => {
                // GIVEN

                spyOn(service, 'find').and.returnValue(Observable.of(new HttpResponse({
                    body: new LayslipRollDetails(123)
                })));

                // WHEN
                comp.ngOnInit();

                // THEN
                expect(service.find).toHaveBeenCalledWith(123);
                expect(comp.layslipRollDetails).toEqual(jasmine.objectContaining({id: 123}));
            });
        });
    });

});
