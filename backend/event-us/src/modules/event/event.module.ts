import { Module } from '@nestjs/common';
import { MongooseModule } from '@nestjs/mongoose';
import { EventController } from './event.controller';
import { EventService } from './event.service';
import { UserEvent, UserEventSchema } from './event.model';
import { User, UserSchema } from '../user/user.model';
import { UserService } from '../user/user.service';

@Module({
  imports: [MongooseModule.forFeature([
    { name: UserEvent.name, schema: UserEventSchema },
    { name: User.name, schema: UserSchema } ])],
    controllers: [EventController],
    providers: [EventService, UserService],
  })
export class EventModule {}