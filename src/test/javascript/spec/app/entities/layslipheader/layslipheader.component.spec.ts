/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, async } from '@angular/core/testing';
import { Observable } from 'rxjs/Observable';
import { HttpHeaders, HttpResponse } from '@angular/common/http';

import { LayslipTestModule } from '../../../test.module';
import { LayslipheaderComponent } from '../../../../../../main/webapp/app/entities/layslipheader/layslipheader.component';
import { LayslipheaderService } from '../../../../../../main/webapp/app/entities/layslipheader/layslipheader.service';
import { Layslipheader } from '../../../../../../main/webapp/app/entities/layslipheader/layslipheader.model';

describe('Component Tests', () => {

    describe('Layslipheader Management Component', () => {
        let comp: LayslipheaderComponent;
        let fixture: ComponentFixture<LayslipheaderComponent>;
        let service: LayslipheaderService;

        beforeEach(async(() => {
            TestBed.configureTestingModule({
                imports: [LayslipTestModule],
                declarations: [LayslipheaderComponent],
                providers: [
                    LayslipheaderService
                ]
            })
            .overrideTemplate(LayslipheaderComponent, '')
            .compileComponents();
        }));

        beforeEach(() => {
            fixture = TestBed.createComponent(LayslipheaderComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(LayslipheaderService);
        });

        describe('OnInit', () => {
            it('Should call load all on init', () => {
                // GIVEN
                const headers = new HttpHeaders().append('link', 'link;link');
                spyOn(service, 'query').and.returnValue(Observable.of(new HttpResponse({
                    body: [new Layslipheader(123)],
                    headers
                })));

                // WHEN
                comp.ngOnInit();

                // THEN
                expect(service.query).toHaveBeenCalled();
                expect(comp.layslipheaders[0]).toEqual(jasmine.objectContaining({id: 123}));
            });
        });
    });

});
