import { UnitOfMeasure } from "./unit-of-measure.interface";

export interface Item{
    id?: number;
    code: string;
    description:string;
    price:number;
    unitOfMeasure: UnitOfMeasure;
}