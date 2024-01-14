import { Module } from '@nestjs/common';

import { MongooseModule } from '@nestjs/mongoose';
import { UserModule } from './modules/user/user.module';

@Module({
  imports: [ MongooseModule.forRoot('mongodb+srv://zivmorgan:ZfgRRn3HDEj9mC4d@cluster0.c5gbgne.mongodb.net/?retryWrites=true&w=majority/EventUs'), UserModule],
  controllers: [],
  providers: [],
})
export class AppModule {}
