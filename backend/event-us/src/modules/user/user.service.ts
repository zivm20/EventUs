import { Injectable, NotFoundException } from '@nestjs/common';
import { InjectModel } from '@nestjs/mongoose';
import { Model } from 'mongoose';
import { User } from './user.model';
import { UserEvent } from '../event/event.model';
import { ProfilePic } from '../profilePic/profilePic.model';

import { CreateUserDto } from '../dto/user.dto';
import { Id } from '../dto/id.dto'
import { Message } from '../message/message.model';


@Injectable()
export class UserService {
  constructor(
    @InjectModel(User.name) private readonly userModel: Model<User>,
    ) {}

  async createUser(createUserDto: CreateUserDto): Promise<User> {
    console.log("creating user" + createUserDto);
    const createdUser = new this.userModel(createUserDto);
    return createdUser.save();
  }

  async findAllUsers(): Promise<User[]> {
    return this.userModel.find().exec();
  }
  async printAllUsers(): Promise<void>{
    const users: User[] = await this.userModel.find().exec();
    
    users.forEach( (user,index)=>{
      console.log(user);
    });
  }


  // TODO: error handling
  /**
   * Get all messages by ids
   * @param _ids List of _id field of desired messages
   * @returns List of desired messages
   */
  async getUsers(_ids:Id[]): Promise<User[]>{
    return await this.userModel.find({ _id: { $in: _ids } }).exec();
  }
  
  async getUser(_id:Id, field?:string): Promise<User>{
    return this.userModel.findById(_id,field).exec().then((user) => { 
      if (!user) throw new NotFoundException('User '+_id+' not Found');
      return user;
    })
  }


  async getEventIds(_id: Id): Promise<Id[]> {
    return (await this.getUser(_id,'events')).events; 
  }
  
  async getProfilePic(_id: Id): Promise<Id> {
    
    return (await this.getUser(_id,'profilePic')).profilePic;
  }
  async getMessages(_id: Id): Promise<Id[]> {
    return (await this.getUser(_id,'messages')).messages;
  }
  
  

  // Implement other CRUD operations as needed
}