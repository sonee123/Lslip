import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs/Observable';
import { SERVER_API_URL } from '../../app.constants';

import { LayslipRollDetails } from './layslip-roll-details.model';
import { createRequestOption } from '../../shared';

export type EntityResponseType = HttpResponse<LayslipRollDetails>;

@Injectable()
export class LayslipRollDetailsService {

    private resourceUrl =  SERVER_API_URL + 'api/layslip-roll-details';

    constructor(private http: HttpClient) { }

    create(layslipRollDetails: LayslipRollDetails): Observable<EntityResponseType> {
        const copy = this.convert(layslipRollDetails);
        return this.http.post<LayslipRollDetails>(this.resourceUrl, copy, { observe: 'response' })
            .map((res: EntityResponseType) => this.convertResponse(res));
    }

    update(layslipRollDetails: LayslipRollDetails): Observable<EntityResponseType> {
        const copy = this.convert(layslipRollDetails);
        return this.http.put<LayslipRollDetails>(this.resourceUrl, copy, { observe: 'response' })
            .map((res: EntityResponseType) => this.convertResponse(res));
    }

    find(id: number): Observable<EntityResponseType> {
        return this.http.get<LayslipRollDetails>(`${this.resourceUrl}/${id}`, { observe: 'response'})
            .map((res: EntityResponseType) => this.convertResponse(res));
    }

    query(req?: any): Observable<HttpResponse<LayslipRollDetails[]>> {
        const options = createRequestOption(req);
        return this.http.get<LayslipRollDetails[]>(this.resourceUrl, { params: options, observe: 'response' })
            .map((res: HttpResponse<LayslipRollDetails[]>) => this.convertArrayResponse(res));
    }

    delete(id: number): Observable<HttpResponse<any>> {
        return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response'});
    }

    private convertResponse(res: EntityResponseType): EntityResponseType {
        const body: LayslipRollDetails = this.convertItemFromServer(res.body);
        return res.clone({body});
    }

    private convertArrayResponse(res: HttpResponse<LayslipRollDetails[]>): HttpResponse<LayslipRollDetails[]> {
        const jsonResponse: LayslipRollDetails[] = res.body;
        const body: LayslipRollDetails[] = [];
        for (let i = 0; i < jsonResponse.length; i++) {
            body.push(this.convertItemFromServer(jsonResponse[i]));
        }
        return res.clone({body});
    }

    /**
     * Convert a returned JSON object to LayslipRollDetails.
     */
    private convertItemFromServer(layslipRollDetails: LayslipRollDetails): LayslipRollDetails {
        const copy: LayslipRollDetails = Object.assign({}, layslipRollDetails);
        return copy;
    }

    /**
     * Convert a LayslipRollDetails to a JSON which can be sent to the server.
     */
    private convert(layslipRollDetails: LayslipRollDetails): LayslipRollDetails {
        const copy: LayslipRollDetails = Object.assign({}, layslipRollDetails);
        return copy;
    }
}
