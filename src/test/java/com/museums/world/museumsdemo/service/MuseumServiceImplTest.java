package com.museums.world.museumsdemo.service;

import com.museums.world.museumsdemo.domain.MuseumDto;
import com.museums.world.museumsdemo.domain.WorkDto;
import com.museums.world.museumsdemo.exception.ResourceNotFoundException;
import com.museums.world.museumsdemo.mapper.MuseumMapper;
import com.museums.world.museumsdemo.mapper.WorkMapper;
import com.museums.world.museumsdemo.model.Museum;
import com.museums.world.museumsdemo.model.Work;
import com.museums.world.museumsdemo.repository.MuseumRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class MuseumServiceImplTest {

    @Mock
    private MuseumRepository museumRepository;

    @Mock
    private MuseumMapper museumMapper;

    @Mock
    private WorkMapper workMapper;

    @InjectMocks
    private MuseumServiceImpl museumServiceImpl;

    @Captor
    private ArgumentCaptor<Integer> museumIdCaptor;

    @Captor
    private ArgumentCaptor<MuseumDto> museumDtoCaptor;

    private Museum museum;

    private Work work;

    @BeforeEach
    void setUp() {
        work = Work.builder()
                .id(1)
                .name("Dummy artwork")
                .artist("Dummy artist")
                .medium("Dummy medium")
                .period("Dummy period")
                .build();

        museum = Museum.builder()
                .id(1)
                .name("dummy museum")
                .address("dummy address")
                .phone("dummy phone")
                .founded("1984")
                .works(Collections.singletonList(work))
                .build();
    }

    @Test
    void findMuseumWithWorks() {
        when(museumRepository.findMuseumWithWorksById(museumIdCaptor.capture()))
                .thenReturn(Optional.of(MuseumDto.builder().works(Collections.singletonList(new WorkDto())).build()));
        when(museumMapper.dtoToModel(any(MuseumDto.class))).thenReturn(museum);
        when(workMapper.dtoToModel(any(WorkDto.class))).thenReturn(work);

        Museum queriedMuseum = museumServiceImpl.findMuseumWithWorks(1);

        verify(museumRepository, times(1)).findMuseumWithWorksById(any(Integer.class));
        assertEquals(1, museumIdCaptor.getValue());

        verify(museumMapper, times(1)).dtoToModel(any(MuseumDto.class));
        verify(workMapper, times(1)).dtoToModel(any(WorkDto.class));

        assertThat(museum.getId(), equalTo(queriedMuseum.getId()));
        assertThat(museum.getName(), equalTo(queriedMuseum.getName()));
        assertThat(museum.getAddress(), equalTo(queriedMuseum.getAddress()));
        assertThat(museum.getFounded(), equalTo(queriedMuseum.getFounded()));
        assertThat(museum.getPhone(), equalTo(queriedMuseum.getPhone()));
        assertThat(museum.getWorks(), hasSize(1));
        assertThat(museum.getWorks().get(0).getId(), equalTo(work.getId()));
        assertThat(museum.getWorks().get(0).getName(), equalTo(work.getName()));
        assertThat(museum.getWorks().get(0).getArtist(), equalTo(work.getArtist()));
        assertThat(museum.getWorks().get(0).getPeriod(), equalTo(work.getPeriod()));
        assertThat(museum.getWorks().get(0).getMedium(), equalTo(work.getMedium()));
    }

    @Test
    void findMuseumWithWorks_MuseumDoesNotExist() {
        when(museumRepository.findMuseumWithWorksById(museumIdCaptor.capture())).thenReturn(Optional.empty());

        assertThrows(ResourceNotFoundException.class, () -> museumServiceImpl.findMuseumWithWorks(1));

        verify(museumRepository, times(1)).findMuseumWithWorksById(any(Integer.class));
        assertEquals(1, museumIdCaptor.getValue());
        verify(museumMapper, never()).dtoToModel(any(MuseumDto.class));
        verify(workMapper, never()).dtoToModel(any(WorkDto.class));
    }

    @Test
    void findAllMuseumsWithWorks() {
        MuseumDto museumDto = MuseumDto.builder().id(1).works(Collections.singletonList(new WorkDto())).build();

        when(museumRepository.findAllMuseumsWithWorks())
                .thenReturn(Optional.of(Collections.singletonList(museumDto)));
        when(museumMapper.dtoToModel(any(MuseumDto.class))).thenReturn(museum);
        when(workMapper.dtoToModel(any(WorkDto.class))).thenReturn(work);

        List<Museum> queriedMuseums = museumServiceImpl.findAllMuseumsWithWorks();

        verify(museumRepository, times(1)).findAllMuseumsWithWorks();
        verify(museumMapper, times(1)).dtoToModel(any(MuseumDto.class));
        verify(workMapper, times(1)).dtoToModel(any(WorkDto.class));

        assertThat(queriedMuseums, hasSize(1));
        assertThat(queriedMuseums.get(0), equalTo(museum));
    }

    @Test
    void saveMuseum() {
        museum.setWorks(null);

        when(museumMapper.modelToDto(any(Museum.class))).thenReturn(MuseumDto.builder().build());
        when(museumRepository.save(any(MuseumDto.class))).thenReturn(MuseumDto.builder().build());
        when(museumMapper.dtoToModel(any(MuseumDto.class))).thenReturn(museum);

        Museum savedMuseum = museumServiceImpl.saveMuseum(museum);

        verify(museumMapper, times(1)).modelToDto(any(Museum.class));
        verify(museumRepository, times(1)).save(any(MuseumDto.class));
        verify(museumMapper, times(1)).dtoToModel(any(MuseumDto.class));

        assertThat(savedMuseum, equalTo(museum));
    }

    @Test
    void updateMuseum() {
        museum.setWorks(null);

        when(museumRepository.findById(museumIdCaptor.capture())).thenReturn(Optional.of(MuseumDto.builder().build()));
        when(museumMapper.modelToDto(any(Museum.class))).thenReturn(MuseumDto.builder().build());
        when(museumRepository.save(museumDtoCaptor.capture())).thenReturn(MuseumDto.builder().build());
        when(museumMapper.dtoToModel(any(MuseumDto.class))).thenReturn(museum);

        Museum savedMuseum = museumServiceImpl.updateMuseum(1, museum);

        verify(museumMapper, times(1)).modelToDto(any(Museum.class));
        verify(museumRepository, times(1)).findById(any(Integer.class));
        assertThat(museumIdCaptor.getValue(), equalTo(1));
        verify(museumRepository, times(1)).save(any(MuseumDto.class));
        assertThat(museumDtoCaptor.getValue(), is(notNullValue()));
        verify(museumMapper, times(1)).dtoToModel(any(MuseumDto.class));

        assertThat(savedMuseum, equalTo(museum));
    }

    @Test
    void updateMuseum_MuseumNotFound() {
        museum.setWorks(null);

        when(museumRepository.findById(museumIdCaptor.capture())).thenReturn(Optional.empty());

        assertThrows(ResourceNotFoundException.class, () -> museumServiceImpl.updateMuseum(1, museum));

        verify(museumRepository, times(1)).findById(any(Integer.class));
        assertThat(museumIdCaptor.getValue(), equalTo(1));
    }

    @Test
    void deleteMuseum() {
        doNothing().when(museumRepository).deleteById(museumIdCaptor.capture());

        museumServiceImpl.deleteMuseum(1);

        verify(museumRepository, times(1)).deleteById(any(Integer.class));
        assertThat(museumIdCaptor.getValue(), equalTo(1));
    }

}