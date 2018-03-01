/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, async } from '@angular/core/testing';
import { Observable } from 'rxjs/Observable';
import { HttpHeaders, HttpResponse } from '@angular/common/http';

import { LayslipTestModule } from '../../../test.module';
import { LayslipRollDetailsComponent } from '../../../../../../main/webapp/app/entities/layslip-roll-details/layslip-roll-details.component';
import { LayslipRollDetailsService } from '../../../../../../main/webapp/app/entities/layslip-roll-details/layslip-roll-details.service';
import { LayslipRollDetails } from '../../../../../../main/webapp/app/entities/layslip-roll-details/layslip-roll-details.model';

describe('Component Tests', () => {

    describe('LayslipRollDetails Management Component', () => {
        let comp: LayslipRollDetailsComponent;
        let fixture: ComponentFixture<LayslipRollDetailsComponent>;
        let service: LayslipRollDetailsService;

        beforeEach(async(() => {
            TestBed.configureTestingModule({
                imports: [LayslipTestModule],
                declarations: [LayslipRollDetailsComponent],
                providers: [
                    LayslipRollDetailsService
                ]
            })
            .overrideTemplate(LayslipRollDetailsComponent, '')
            .compileComponents();
        }));

        beforeEach(() => {
            fixture = TestBed.createComponent(LayslipRollDetailsComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(LayslipRollDetailsService);
        });

        describe('OnInit', () => {
            it('Should call load all on init', () => {
                // GIVEN
                const headers = new HttpHeaders().append('link', 'link;link');
                spyOn(service, 'query').and.returnValue(Observable.of(new HttpResponse({
                    body: [new LayslipRollDetails(123)],
                    headers
                })));

                // WHEN
                comp.ngOnInit();

                // THEN
                expect(service.query).toHaveBeenCalled();
                expect(comp.layslipRollDetails[0]).toEqual(jasmine.objectContaining({id: 123}));
            });
        });
    });

});
