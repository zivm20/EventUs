import {Id} from './id.dto'

enum UserType {attendent, creator};
export class CreateUserDto {
    readonly _id: Id;
    readonly pfp_id: Id;
    readonly name: string;
    readonly email: string;
    readonly password: string;
    readonly userType: UserType;
    readonly messages: Id[];
    readonly events: Id[];

  }